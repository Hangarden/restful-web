package com.example.restfulweb.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class UserDaoService {
    private static List<UserV1> userV1s = new ArrayList<>();

    private static int usersCount = 3;

    static {
        userV1s.add(new UserV1(1, "Kenneth", new Date(), "pass1", "701701-123445"));
        userV1s.add(new UserV1(2, "Alice", new Date(), "pass2", "801701-123445"));
        userV1s.add(new UserV1(3, "Elena", new Date(), "pass3", "901701-123445"));
    }

    public List<UserV1> findAll() {
        return userV1s;
    }

    public UserV1 findOne(int id) {
        for (UserV1 userV1 : userV1s) {
            if (userV1.getId() == id) {
                return userV1;
            }
        }

        return null;
    }

    public UserV1 save (UserV1 userV1) {
        if (userV1.getId() == null) {
            userV1.setId(++usersCount);
        }

        userV1s.add(userV1);
        return userV1;
    }

    public UserV1 deleteById(int id) {
        Iterator<UserV1> iterator = userV1s.iterator();

        while (iterator.hasNext()) {
            UserV1 userV1 = iterator.next();

            if (userV1.getId() ==id) {
                iterator.remove();
                return userV1;
            }
        }

        return null;
    }


}
