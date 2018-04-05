package com.example.natallialemiasheuskaya.userretrofit.base;

public abstract class BaseItemViewModel<Model> {

    public abstract void setItem(Model model,int position);

    public void init(){
       //во время создания
    }
    public void release(){
        //уничтожение
    }
}
