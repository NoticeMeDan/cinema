package com.noticemedan.cinema;

import com.noticemedan.cinema.controller.ShowController;
import com.noticemedan.cinema.entity.ShowEntity;
import com.noticemedan.cinema.service.BaseService;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.List;

public class ShowTest extends BaseService {
    private ShowController showController;

    @BeforeMethod
    public void setUp() throws Exception {
        this.showController = new ShowController();
    }

    @Test
    public void testShow() throws Exception {
        ShowEntity showEntity;
        List<ShowEntity> showsByDate = this.showController.getAvailableShowsByDate(String.valueOf(LocalDate.now()));
        ShowEntity show = this.showController.getSeatShow(1);
    }
}
