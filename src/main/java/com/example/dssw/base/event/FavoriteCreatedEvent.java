package com.example.dssw.base.event;

import com.example.dssw.model.FavoriteBinEntity;
import org.springframework.context.ApplicationEvent;

public class FavoriteCreatedEvent extends ApplicationEvent {
    private FavoriteBinEntity fav;

    public FavoriteCreatedEvent(Object source, FavoriteBinEntity fav){
        super(source);
        this.fav = fav;
    }

    public FavoriteBinEntity getFav() { return fav; }
}
