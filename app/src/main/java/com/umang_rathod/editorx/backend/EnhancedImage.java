package com.umang_rathod.editorx.backend;

public class EnhancedImage {
    private String image_url;
    private String type;
    private int scale_factor;
    private int return_type;

    public EnhancedImage(String image_url, String type, int scale_factor, int return_type) {
        this.image_url = image_url;
        this.type = type;
        this.scale_factor = scale_factor;
        this.return_type = return_type;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getType() {
        return type;
    }

    public int getScale_factor() {
        return scale_factor;
    }

    public int getReturn_type() {
        return return_type;
    }
}
























//package com.umang_rathod.editorx.backend;
//
//public class EnhancedImage {
//    public int getStatus() {
//        return status;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    private int status;
//    private String message;
//    class data{
//        private double completed_at;
//        private double created_at;
//        private String img;
//        private double processed_at;
//        private int progress;
//
//        public data(double completed_at, double created_at, String img, double processed_at, int progress, int return_type, int state, String task_id, String type) {
//            this.completed_at = completed_at;
//            this.created_at = created_at;
//            this.img = img;
//            this.processed_at = processed_at;
//            this.progress = progress;
//            this.return_type = return_type;
//            this.state = state;
//            this.task_id = task_id;
//            this.type = type;
//        }
//
//        public double getCompleted_at() {
//            return completed_at;
//        }
//
//        public double getCreated_at() {
//            return created_at;
//        }
//
//        public String getImg() {
//            return img;
//        }
//
//        public double getProcessed_at() {
//            return processed_at;
//        }
//
//        public int getProgress() {
//            return progress;
//        }
//
//        public int getReturn_type() {
//            return return_type;
//        }
//
//        public int getState() {
//            return state;
//        }
//
//        public String getTask_id() {
//            return task_id;
//        }
//
//        public String getType() {
//            return type;
//        }
//
//        private int return_type;
//        private int state;
//        private String task_id;
//        private String type;
//
//    }
//
//    public EnhancedImage(int status, String message) {
//        this.status = status;
//        this.message = message;
//    }
//}
