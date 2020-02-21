package com.andreyjig.clothingstore.mvp.model.handler;


public interface DataHandler<T> {

    void setDownloadedData(T data);
    void setErrorDownloaded(int errorStringId);

}
