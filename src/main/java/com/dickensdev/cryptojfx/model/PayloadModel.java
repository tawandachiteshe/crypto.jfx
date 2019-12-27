package com.dickensdev.cryptojfx.model;

import java.util.ArrayList;

public class PayloadModel {
    private ArrayList<CryptoModel> payload;


    public ArrayList<CryptoModel> getPayload() {
        return payload;
    }

    public class Logo {
        private String mimeType;
        private String imageData;

        public String getMimeType() {
            return mimeType;
        }

        public String getImageData() {
            return imageData;
        }
    }
}
