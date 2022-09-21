package com.kenzie.appserver.service.model;

public class Art {

        String artId;
        String name;
        String artistName;
        String locationId;
        String medium;
        String type;
        boolean humiditySensitive;
        String timeStamp;
        String history;
        String timeSpentInStorage;

        public Art(String artId, String name, String artistName, String locationId, String medium, String type,
                         boolean humiditySensitive, String timeStamp, String history, String timeSpentInStorage) {
            this.artId = artId;
            this.name = name;
            this.artistName = artistName;
            this.locationId = locationId;
            this.medium = medium;
            this.type = type;
            this.humiditySensitive = humiditySensitive;
            this.timeStamp = timeStamp;
            this.history = history;
            this.timeSpentInStorage = timeSpentInStorage;
        }

        public Art(String artId) {
            this.artId = artId;

        }

        public Art() {
        }

        public String getArtId() {
            return artId;
        }

        public void setArtId(String artId) {
            this.artId = artId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getArtistName() {
            return artistName;
        }

        public void setArtistName(String artistName) {
            this.artistName = artistName;
        }

        public String getLocationId() {
            return locationId;
        }

        public void setLocationId(String locationId) {
            this.locationId = locationId;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public boolean isHumiditySensitive() {
            return humiditySensitive;
        }

        public void setHumiditySensitive(boolean humiditySensitive) {
            this.humiditySensitive = humiditySensitive;
        }

        public String getTimeStamp() {
            return this.timeStamp;
        }

        public void setTimeStamp(String timeStamp) {
            this.timeStamp = timeStamp;
        }

        public String getHistory() {
            return history;
        }

        public void setHistory(String history) {
            this.history = history;
        }

        public String getTimeSpentInStorage() {
            return timeSpentInStorage;
        }

        public void setTimeSpentInStorage(String timeSpentInStorage) {
            this.timeSpentInStorage = timeSpentInStorage;
        }



}
