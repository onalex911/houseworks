public class Coord {
        private int x;
        private int y;

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public String toString(){
            return "x="+x+"; y="+y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public static boolean isStatused(Coord cell){
            if(cell.getX() != -1 || cell.getY() != -1)
                return true;
            return false;
    }

}
