package com.rookie.bigdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Classname ShiroApplication
 * @Description TODO
 *
 * 测试方式：http://localhost:8085/sys/login
 *  body:{
 *     "username":"admin",
 *     "password":"123456"
 * }
 * 根据  返回的token
 * "Authorization": "eyJhbGciOiJIUzUxMiJ9.eyJwYXNzd29yZCI6IiRzaGlybzEkU0hBLTI1NiQ1MDAwMDAkTjZ3aDMxQ3RrenVUZVhOeU5FblBjdz09JFJKNXgwSWZaN0dRYTlRU2pQY3BkMjlrM0dNNElwTkxrSXpLM1g0bDFSMGM9IiwiaWQiOjEsInVzZXJuYW1lIjoiYWRtaW4iLCJzdWIiOiJhZG1pbiIsImV4cCI6MTYyNjQwMDE4MywiaWF0IjoxNjI2MzEzNzgzLCJpc3MiOiJyb29raWUifQ.hs2Di5679RQCMWkDSnyqwQfTYtHZH1CxTeq_LNEyvPJlAhypPnh-dKLBA-kfWs17-iYykPP871DEt6VwnoPZZA""Authorization": "eyJhbGciOiJIUzUxMiJ9.eyJwYXNzd29yZCI6IiRzaGlybzEkU0hBLTI1NiQ1MDAwMDAkTjZ3aDMxQ3RrenVUZVhOeU5FblBjdz09JFJKNXgwSWZaN0dRYTlRU2pQY3BkMjlrM0dNNElwTkxrSXpLM1g0bDFSMGM9IiwiaWQiOjEsInVzZXJuYW1lIjoiYWRtaW4iLCJzdWIiOiJhZG1pbiIsImV4cCI6MTYyNjQwMDE4MywiaWF0IjoxNjI2MzEzNzgzLCJpc3MiOiJyb29raWUifQ.hs2Di5679RQCMWkDSnyqwQfTYtHZH1CxTeq_LNEyvPJlAhypPnh-dKLBA-kfWs17-iYykPP871DEt6VwnoPZZA""Authorization": "eyJhbGciOiJIUzUxMiJ9.eyJwYXNzd29yZCI6IiRzaGlybzEkU0hBLTI1NiQ1MDAwMDAkTjZ3aDMxQ3RrenVUZVhOeU5FblBjdz09JFJKNXgwSWZaN0dRYTlRU2pQY3BkMjlrM0dNNElwTkxrSXpLM1g0bDFSMGM9IiwiaWQiOjEsInVzZXJuYW1lIjoiYWRtaW4iLCJzdWIiOiJhZG1pbiIsImV4cCI6MTYyNjQwMDE4MywiaWF0IjoxNjI2MzEzNzgzLCJpc3MiOiJyb29raWUifQ.hs2Di5679RQCMWkDSnyqwQfTYtHZH1CxTeq_LNEyvPJlAhypPnh-dKLBA-kfWs17-iYykPP871DEt6VwnoPZZA""Authorization": "eyJhbGciOiJIUzUxMiJ9.eyJwYXNzd29yZCI6IiRzaGlybzEkU0hBLTI1NiQ1MDAwMDAkTjZ3aDMxQ3RrenVUZVhOeU5FblBjdz09JFJKNXgwSWZaN0dRYTlRU2pQY3BkMjlrM0dNNElwTkxrSXpLM1g0bDFSMGM9IiwiaWQiOjEsInVzZXJuYW1lIjoiYWRtaW4iLCJzdWIiOiJhZG1pbiIsImV4cCI6MTYyNjQwMDE4MywiaWF0IjoxNjI2MzEzNzgzLCJpc3MiOiJyb29raWUifQ.hs2Di5679RQCMWkDSnyqwQfTYtHZH1CxTeq_LNEyvPJlAhypPnh-dKLBA-kfWs17-iYykPP871DEt6VwnoPZZA"
 *
 * 添加到请求的header中
 * 调用  http://localhost:8085/api/test/test和http://localhost:8085/api/test/hello进行 测试
 *
 * @Author rookie
 * @Date 2021/7/13 11:41
 * @Version 1.0
 */
@SpringBootApplication
public class ShiroApplication {


    public static void main(String[] args) {
        SpringApplication.run(ShiroApplication.class, args);
    }
}
