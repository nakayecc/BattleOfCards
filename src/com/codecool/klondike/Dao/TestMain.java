package com.codecool.klondike.Dao;

public class TestMain {
    public static void main(String[] args){
        CardsDaoXml cardsDao = new CardsDaoXml();
        cardsDao.addCards();

        for(Object card : cardsDao.getCards()){
            System.out.println(card);
        }
    }
}
