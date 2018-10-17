/*
 * Copyright 2015-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gshp.cosmo;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Service;

import com.microsoft.azure.documentdb.IndexingMode;
import com.microsoft.azure.spring.data.cosmosdb.core.mapping.Document;
import com.microsoft.azure.spring.data.cosmosdb.core.mapping.DocumentIndexingPolicy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "mycollection")
@DocumentIndexingPolicy(mode = IndexingMode.Lazy)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Service
public class Users {

    @Id
    private String id;

    private String email;

    private String name;

    private Long count;

   
    @Override
    public String toString() {
        return String.format("%s: %s %s %s", this.id, this.email, this.name);
    }
}

