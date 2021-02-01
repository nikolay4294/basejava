import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int countResume;

    void clear() {
        for(int i = 0; i < countResume; i++) {
            storage[i] = null;
        }
        countResume = 0;
    }

    void save(Resume r) {
        storage[countResume] = r;
        countResume++;


    }

    Resume get(String uuid) {
        for(int i = 0; i < countResume; i++) {
            if(storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for(int i = 0; i < countResume; i++) {
            if (storage[i].uuid.equals(uuid)) {
                System.out.println(storage[i].uuid);
                storage[i] = null;
            }
                for(int j = i; j < countResume; j++) {
                    storage[j] = storage[j+1];
                }
        countResume--;
        break;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, countResume);
    }

    int size() {
        return countResume;
    }
}
