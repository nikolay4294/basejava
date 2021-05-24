package com.urise.webapp.model;

import java.util.Collections;
import java.util.List;

public class Company {

    private final Experience experience = new Experience();
    private final List<Experience> list = Collections.singletonList(experience);
}
