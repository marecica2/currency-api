//package balla.marek.currencies.importer;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.item.file.FlatFileItemReader;
//import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
//import org.springframework.batch.item.file.mapping.DefaultLineMapper;
//import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//
//@EnableBatchProcessing
//@Configuration
//public class CsvImportJobConfiguration {
//
//    @Autowired
//    public JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    public StepBuilderFactory stepBuilderFactory;
//
//    @Autowired
//    public DataSource dataSource;
//
//
//    @Bean
//    public FlatFileItemReader<Map<String, Object>> csvAnimeReader() {
//        FlatFileItemReader<Map<String, Object>> reader = new FlatFileItemReader<>();
//        reader.setResource(new ClassPathResource("eurofxref.csv"));
//        reader.setLineMapper(new DefaultLineMapper<>() {{
//            setLineTokenizer(new DelimitedLineTokenizer() {{
//                setNames(new String[]{"date", "title", "description"});
//            }});
//            setFieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
//                setTargetType(HashMap<String, Object>.class);
//            }});
//        }});
//        return reader;
//    }
//
//
//    @Bean
//    ItemProcessor<AnimeDTO, AnimeDTO> csvAnimeProcessor() {
//        return new AnimeProcessor();
//    }
//
//    @Bean
//    public JdbcBatchItemWriter<AnimeDTO> csvAnimeWriter() {
//        JdbcBatchItemWriter<AnimeDTO> csvAnimeWriter = new JdbcBatchItemWriter<AnimeDTO>();
//        csvAnimeWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<AnimeDTO>());
//        csvAnimeWriter.setSql("INSERT INTO animes (id, title, description) VALUES (:id, :title, :description)");
//        csvAnimeWriter.setDataSource(dataSource);
//        return csvAnimeWriter;
//    }
//
//    // end reader, writer, and processor
//
//    // begin job info
//    @Bean
//    public Step csvFileToDatabaseStep() {
//        return stepBuilderFactory.get("csvFileToDatabaseStep")
//                .<Map<String, Object>, AnimeDTO>chunk(1)
//                .reader(csvAnimeReader())
//                .processor(csvAnimeProcessor())
//                .writer(csvAnimeWriter())
//                .build();
//    }
//
//    @Bean
//    Job csvFileToDatabaseJob() {
//        return jobBuilderFactory.get("csvFileToDatabaseJob")
//                                .incrementer(new RunIdIncrementer())
//                                .listener(listener)
//                                .flow(csvFileToDatabaseStep())
//                                .end()
//                                .build();
//    }
//}
