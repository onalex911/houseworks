public class CoordArray {

        private int capacity;
        private int size;
        private Coord[] arr;
        {
            capacity = 9;
            size=0;
        }

        public CoordArray() {
            this.arr = new Coord[capacity];
        }

        public void add(Coord val){
            this.arr[size++] = val;
        }

        private void checkCapacity(int size){
            if(size >= capacity){
                capacity *= 2;
                Coord[] tmp = new Coord[capacity];
                for (int i = 0; i < this.size; i++) {
                    tmp[i] = this.arr[i];
                }
                this.arr = tmp;
            }
        }

        public Coord[] getArr() {
            return arr;
        }

        public Coord getCellByIndex(int index) {
            return arr[index];
        }

        public int getSize() {
            return size;
        }
}
