package com.Confess_Your_Feelings;


import java.util.List;


public interface ConfessionService {

    Confession saveConfession(Confession confession);
    List<Confession> getAllConfessions();
    Confession getConfessionById(Long id);
    Confession updateConfession(Long id, Confession confessionDetails);
    void deleteConfession(Long id);
}

	

