package com.example.javatest;


import java.util.ArrayList;

public class myClass {
    public static void main(String[] arg){
         WeatherData weatherData = new WeatherData();
         CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherData);
         weatherData.setMeasurements(1,2,3);
    }
}

interface  Subject{
    void registerObserver(MObserver o);
    void removeObserver(MObserver o);
//    void notifyObservers();
}
interface  MObserver{
    void update(float temp,float humidity,float pressure);
}
interface  DisplayElement{
    void display();
}

class WeatherData implements Subject{
  ArrayList observers;
  float temperature;
  float humidity;
  float pressure;
  WeatherData(){
      observers = new ArrayList();
  }


    @Override
    public void registerObserver(MObserver o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(MObserver o) {

    }

    public void notifyObservers() {
        for(int i = 0;i<observers.size();i++){
            MObserver observer = (MObserver) observers.get(i);
            observer.update(temperature,humidity,pressure);
        }
    }

    private void measurementChange(){
       notifyObservers();

     }
      public  void setMeasurements(float temperature,float humidity,float pressure){
      this.temperature = temperature;
      this.humidity = humidity;
      this.pressure = pressure;
      measurementChange();
      }
}




class CurrentConditionsDisplay implements  MObserver,DisplayElement{
    float temperature;
    float humidity;
    Subject weatherData;
    CurrentConditionsDisplay(Subject weatherData){
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        this.temperature = temp;
        this.humidity = humidity;
        display();
    }

    @Override
    public void display() {
        System.out.println("Current conditions:temperature===="+temperature+"And humidity ==== "+humidity);
    }
}


