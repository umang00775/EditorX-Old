package com.umang_rathod.editorx.backend;

import com.google.gson.annotations.SerializedName;

public class Colorization {
    private int status;
    @SerializedName("message")
    private String msg;

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    class Tuple{
        private int completed_at;
        private int created_at;
        private String image;
        private int processed_at;
        private int progress;
        private int return_type;
        private int state;
        private String task_id;

        public int getCompleted_at() {
            return completed_at;
        }

        public int getCreated_at() {
            return created_at;
        }

        public String getImage() {
            return image;
        }

        public int getProcessed_at() {
            return processed_at;
        }

        public int getProgress() {
            return progress;
        }

        public int getReturn_type() {
            return return_type;
        }

        public int getState() {
            return state;
        }

        public String getTask_id() {
            return task_id;
        }
    }


}
