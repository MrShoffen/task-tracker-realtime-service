package org.mrshoffen.tasktracker.realtime.client;


import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class PermissionsClient {

    private final RestClient restClient;

//    public Flux<Permission> getUserPermissionsForWorkspace(UUID userId, UUID workspaceId) {
//        return restClient
//                .get()
//                .uri("/internal/permissions/users/{userId}/workspaces/{workspaceId}", userId, workspaceId)
//                .retrieve()
//                .bodyToFlux(Permission.class);
//    }
}
