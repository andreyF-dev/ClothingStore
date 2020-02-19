package com.andreyjig.clothingstore.entity.handler;

import com.andreyjig.clothingstore.entity.Product;

public interface ProductDescription extends ErrorDownload{
    void setDownloadedProduct(Product product);
}
