package com.noticemedan.cinema.service;

import com.noticemedan.cinema.entity.TestEntity;

import java.util.List;
import java.util.stream.Collectors;

public class TestService extends BaseService {
    public TestService() {
        super();
    }

    public final List<TestEntity> getTests() {
        return this.testDao.listTests();
    }

    public final List<TestEntity> getTestsWithEasterEgg() {
        return this.testDao.listTests().stream()
                .map(testEntity -> {
                    testEntity.setLastname(testEntity.getLastname() + " Lugter");
                    return testEntity;
                })
                .collect(Collectors.toList());
    }
}
