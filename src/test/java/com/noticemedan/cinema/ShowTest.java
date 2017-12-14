package com.noticemedan.cinema;

import com.noticemedan.cinema.entity.ShowEntity;
import com.noticemedan.cinema.service.BaseService;
import com.noticemedan.cinema.service.ShowService;
import org.testng.Assert;
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
    public void testGetShow() throws Exception {
        ShowEntity show = null;
        try {
            show = this.showService.getSeatShow(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assert.assertEquals(show.getMovie().getName(), "Rick and Morty the movie");
    }

    @Test
    public void testGetShowsByDate() throws Exception {
        try {
            List<ShowEntity> showsByDate = this.showService.getShowsByDate(String.valueOf(LocalDate.now()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
