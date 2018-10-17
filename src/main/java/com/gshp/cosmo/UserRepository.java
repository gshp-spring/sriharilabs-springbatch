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

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.microsoft.azure.spring.data.cosmosdb.repository.DocumentDbRepository;

@Repository
@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepository extends DocumentDbRepository<Users, String> {

    List<Users> findByName(String firstName);

   // List<Users> findByEmailAndAddress(String email, Address address);

    List<Users> findByEmailOrName(String email, String Name);

    List<Users> findByCount(Long count, Sort sort);

    List<Users> findByNameIn(Collection<String> names);

    //Page<Users> findByAddress(Address address, Pageable pageable);
}

