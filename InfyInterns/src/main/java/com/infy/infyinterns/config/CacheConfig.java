package com.infy.infyinterns.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Caching configuration.
 *
 * Uses simple in-memory ConcurrentMap cache — fine for a single-node application.
 *
 * Cache names used in the service layer:
 *   "mentors"  → caches getMentors() results keyed by numberOfProjectsMentored
 *
 * For production scale-out (multiple nodes / microservices), replace with:
 *   - Redis  : add spring-boot-starter-data-redis → RedisCacheManager
 *   - Caffeine: add com.github.ben-manes.caffeine → CaffeineCacheManager
 *     (supports TTL / size-based eviction)
 */
@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("mentors", "projects");
    }
}