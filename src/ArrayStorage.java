import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int quantityResume;

    void clear() {
        for(int i = 0; i < quantityResume; i++) {
            storage[i] = null;
        }
    }

    void save(Resume r) {
        storage[quantityResume] = r;
        quantityResume++;
    }

    Resume get(String uuid) {
        for(int i = 0; i < quantityResume; i++) {
            if(storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < quantityResume; i++) {
            int num = i;
            if (storage[i].uuid.equals(uuid)) {
                storage[i] = null;
            }
            storage[num] = storage[num+1];
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, quantityResume);
    }

    int size() {
        return quantityResume;
    }
}
