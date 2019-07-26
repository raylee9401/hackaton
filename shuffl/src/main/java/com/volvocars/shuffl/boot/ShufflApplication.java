/**
 * Copyright (c) 2019 Volvo Car Corporation. All rights reserved.
 *
 * NOTICE: This file contains material that is confidential and proprietary to
 * Volvo Cars and/or other developers. No license is granted under any intellectual or
 * industrial property rights of Volvo Cars except as may be provided in an agreement with
 * Volvo Cars. Any unauthorized copying or distribution of content from this file is prohibited.
 */
package com.volvocars.shuffl.boot;

import com.volvocars.shuffl.Controller.DataController;
import com.volvocars.shuffl.db.CassandraConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = {ShufflApplication.class, CassandraConfiguration.class, DataController.class})
public class ShufflApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShufflApplication.class, args);
    }
}
