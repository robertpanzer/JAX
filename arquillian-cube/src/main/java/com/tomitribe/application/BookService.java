/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package com.tomitribe.application;

import com.tomitribe.entities.Book;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Stateless
public class BookService implements IBookService {

    @PersistenceContext(unitName = "book-pu")
    private EntityManager em;

    @Override
    public Book addBook(final Book book) {
        em.persist(book);
        em.flush();
        em.detach(book);
        return book;
    }

    @Override
    public List<Book> getAllBooks() {
        final CriteriaQuery<Book> cq = em.getCriteriaBuilder().createQuery(Book.class);
        cq.select(cq.from(Book.class));
        final List<Book> resultList = em.createQuery(cq).getResultList();
        return resultList;
    }
}