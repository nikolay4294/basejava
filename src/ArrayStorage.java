import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int quantityResumeInStorage;

    void clear() {
        for (int i = 0; i < quantityResumeInStorage; i++) {
            storage[i] = null;
        }
    }

    void save(Resume r) {
        storage[quantityResumeInStorage] = r;
        quantityResumeInStorage++;
    }

    Resume get(String uuid) {
        for (int i = 0; i < quantityResumeInStorage; i++) {
            if (String.valueOf(storage[i]).equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < quantityResumeInStorage; i++) {
            if (String.valueOf(storage[i]).equals(uuid)) {
                storage[i] = null;
            }
        }
        for (int i = 0; i <= 1; i++) {
            Resume tmp = storage[0];
            for (int j = 0; j < storage.length - 1; j++)
                storage[j] = storage[j + 1];
            storage[storage.length - 1] = tmp;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, quantityResumeInStorage);
    }

    int size() {
        return quantityResumeInStorage;
    }
}
