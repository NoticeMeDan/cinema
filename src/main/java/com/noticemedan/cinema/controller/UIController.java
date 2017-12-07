package com.noticemedan.cinema.controller;

import java.util.List;

public class UIController {

    //Getters
    /*
    public String getCustomer(int id){
        return CustomerService.getCustomer();
    }

    public List<OrderEntity> getOrders(CustomerEntity customer){

        return CustomerService.getOrders();
    }
*/
    //This will be changed to public Movie getMovies()
    public void getMovies(){
        System.out.println();
        //ShowService.getMovie();
    }

    public int getAmountOfSeats(){
        return 5;
        // return OrderService.getAmountOfSeats();
    }
    /*
    public Order getOrder(){
        Order.getOrder();
    }
    */

    public void getOrder(){
        System.out.println("This is your order:");
    }

    //Setters
    public void addOrder(){
        System.out.println("New order");
        // OrderService.addOrder();
    }

    public void setAmountOfSeats(){
        System.out.println("You chose seats");
        //OrderService.setAmountOfSeats(amount);
    }

    public void isChecked(){
        System.out.println("Checked");
    }
    /*public boolean setIndividualSeat(){
        boolean isChecked;
        if(seatsChecked.isSelected()){

        }
    } */

    public void chooseTimeSlot(){
        System.out.println("Choose timeslot");
        // /ShowService.chooseTimeSlot();
    }

    //Choose
    public void chooseMovie(){
        System.out.println("Choose movie");
        //ShowService.chooseMovie();
    }

    public void chooseDate(){
        System.out.println("Choose date");
        //ShowService.chooseDate();
    }

    public void chooseSeat(){
        System.out.println("Here you choose seats");
        //OrderService.chooseSeat();
    }

    public void saveSettings(){
        System.out.println("Save");
        //OrderService.saveSettings();
    }

    public void cancelSettings(){
        System.out.println("Cancel");
        //OrderService.cancelSettings();
    }

    public void test(){
        System.out.println("Hey, se det virker");
    }




}
