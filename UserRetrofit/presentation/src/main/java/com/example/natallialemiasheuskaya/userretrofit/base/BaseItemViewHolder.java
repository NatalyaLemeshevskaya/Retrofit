package com.example.natallialemiasheuskaya.userretrofit.base;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.natallialemiasheuskaya.userretrofit.BR;

//кеширование данных(дабы не повторяться)
public abstract class BaseItemViewHolder<Model,
        ViewModel extends BaseItemViewModel,
        Binding extends ViewDataBinding> extends RecyclerView.ViewHolder {

    private Binding binding;
    private ViewModel viewModel;

    public BaseItemViewHolder(Binding binding, ViewModel viewModel) {
        super(binding.getRoot());
        this.binding = binding;
        this.viewModel = viewModel;
        this.viewModel.init();
        initViewModel();

    }

    protected void initViewModel(){
        binding.setVariable(BR.viewModel,viewModel);
    }

    //=OnDindViewHolder
    public void bindTo(Model model,int position){
        viewModel.setItem(model,position);
        //заставить хмл подписаться на вью модел
        binding.executePendingBindings();
    }

    public void release(){
        this.viewModel.release();
    }
}
