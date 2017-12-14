package com.noticemedan.cinema;

import com.noticemedan.cinema.entity.ShowEntity;
import com.noticemedan.cinema.service.BaseService;
import com.noticemedan.cinema.service.ShowService;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.List;

public class ShowTest extends BaseService {
    private ShowService showService;

    @BeforeMethod
    public void setUp() throws Exception {
        this.showService = new ShowService();
    }

    @Test
    public void testShow() throws Exception {
        ShowEntity showEntity;
        List<ShowEntity> showsByDate = this.showService.getShowsByDate(String.valueOf(LocalDate.now()));
        ShowEntity show = this.showService.getSeatShow(1);
    }
}
