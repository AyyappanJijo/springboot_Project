package com.Confess_Your_Feelings;


import java.util.List;


public interface ConfessionService {

    // 🟢 CREATE operation
    Confession saveConfession(Confession confession);

    // 🟡 READ all confessions
    List<Confession> getAllConfessions();

    // 🟣 READ one confession by ID
    Confession getConfessionById(Long id);

    // 🟠 UPDATE a confession
    Confession updateConfession(Long id, Confession confessionDetails);

    // 🔴 DELETE a confession
    void deleteConfession(Long id);
}

	
