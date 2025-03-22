package tech.buildrun.dynamodb.controller;

import io.awspring.cloud.dynamodb.DynamoDbTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest;
import tech.buildrun.dynamodb.dto.ScoreDTO;
import tech.buildrun.dynamodb.entity.PlayerHistory;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/players")
public class PlayerController {

    private DynamoDbTemplate dynamoDbTemplate;

    public PlayerController(DynamoDbTemplate dynamoDbTemplate) {
        this.dynamoDbTemplate = dynamoDbTemplate;
    }

    @PostMapping(value = "/{username}/games")
    public ResponseEntity<Void> insert(@PathVariable(value = "username") String username,
                                       @RequestBody ScoreDTO dto) {

        var entity = PlayerHistory.fromScore(username, dto);

        dynamoDbTemplate.save(entity);

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{username}/games")
    public ResponseEntity<List<PlayerHistory>> list(@PathVariable(value = "username") String username) {

        var key = Key.builder().partitionValue(username).build();

        var condition = QueryConditional.keyEqualTo(key);

        var query = QueryEnhancedRequest.builder()
                .queryConditional(condition)
                .build();

        var history = dynamoDbTemplate.query(query, PlayerHistory.class);

        return ResponseEntity.ok(history.items().stream().toList());
    }

    @GetMapping(value = "/{username}/games/{gameId}")
    public ResponseEntity<PlayerHistory> findById(@PathVariable(value = "username") String username,
                                                  @PathVariable(value = "gameId") String gameId) {

        var entity = dynamoDbTemplate.load(Key.builder()
                .partitionValue(username)
                .sortValue(gameId)
                .build(), PlayerHistory.class);

        if (entity == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(entity);
        }
    }

    @PutMapping(value = "/{username}/games/{gameId}")
    public ResponseEntity<PlayerHistory> update(@PathVariable(value = "username") String username,
                                                @PathVariable(value = "gameId") String gameId,
                                                @RequestBody ScoreDTO dto) {

        var entity = dynamoDbTemplate.load(Key.builder()
                .partitionValue(username)
                .sortValue(gameId)
                .build(), PlayerHistory.class);

        if (entity == null) {
            return ResponseEntity.notFound().build();
        }

        entity.setScore(dto.score());

        dynamoDbTemplate.save(entity);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{username}/games/{gameId}")
    public ResponseEntity<Void> delete(@PathVariable(value = "username") String username,
                                       @PathVariable(value = "gameId") String gameId){

        var entity = dynamoDbTemplate.load(Key.builder()
                .partitionValue(username)
                .sortValue(gameId)
                .build(), PlayerHistory.class);

        if(entity == null){
            return ResponseEntity.notFound().build();
        }

        dynamoDbTemplate.delete(entity);

        return ResponseEntity.noContent().build();
        }
    }

