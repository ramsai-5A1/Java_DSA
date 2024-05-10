class Solution {

    public class Box implements Comparable<Box> {
        public double value;
        public int num;
        public int den;

        public Box(double value, int num, int den) {
            this.value = value;
            this.num = num;
            this.den = den;
        }

        @Override
        public int compareTo(Box other) {
            if (this.value > other.value) {
                return -1;
            } else if (this.value < other.value) {
                return 1;
            }
            return 0;
        }
    }

    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        int n = arr.length;
        int[] result = new int[2];
        PriorityQueue<Box> maxHeap = new PriorityQueue<>();

        for (int index1 = 0; index1 < n; index1++) {
            for (int index2 = index1 + 1; index2 < n; index2++) {
                double curr = (double) arr[index1] / arr[index2];
                maxHeap.add(new Box(curr, arr[index1], arr[index2]));
                if (maxHeap.size() > k) {
                    maxHeap.poll();
                }
            }
        }
        Box curr = maxHeap.poll();
        result[0] = curr.num;
        result[1] = curr.den;
        return result;
    }
}
