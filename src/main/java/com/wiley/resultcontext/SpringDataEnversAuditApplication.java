package com.wiley.resultcontext;

import com.wiley.resultcontext.dto.QuestionDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@SpringBootApplication
@EnableCaching
public class SpringDataEnversAuditApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringDataEnversAuditApplication.class, args);
    }

}
