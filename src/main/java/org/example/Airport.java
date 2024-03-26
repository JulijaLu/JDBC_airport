package org.example;

    public class Airport {
        private int bizId;
        private String bizName;
        private String address;
        private String city;

        public Airport(int bizId, String bizName, String address, String city) {
            this.bizId = bizId;
            this.bizName = bizName;
            this.address = address;
            this.city = city;
        }
        public Airport(int bizId, String bizName, String address) {
            this.bizId = bizId;
            this.bizName = bizName;
            this.address = address;
            this.city = city;
        }

        public Airport(String bizName, String address, String city) {
            this.bizName = bizName;
            this.address = address;
            this.city = city;
        }

        public int getBizId() {
            return this.bizId;
        }

        public String getBizName() {
            return this.bizName;
        }

        public String getAddress() {
            return this.address;
        }

        public String getCity() {
            return this.city;
        }

        public void setBizId(int bizId) {
            this.bizId = bizId;
        }

        public void setBizName(String bizName) {
            this.bizName = bizName;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setCity(String city) {
            this.city = city;
        }

        @Override
        public String toString() {
            return "Airport{" +
                    "bizId=" + bizId +
                    ", bizName='" + bizName + '\'' +
                    ", address='" + address + '\'' +
                    ", city='" + city + '\'' +
                    '}';
        }
    }

