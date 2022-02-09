package poc.inetum.flowable.web.rest;

import poc.inetum.flowable.domain.Message;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.variable.api.history.HistoricVariableInstance;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/rdv")
public class RdvController {

    private RuntimeService runtimeService;
    private HistoryService historyService;

    public RdvController(final RuntimeService runtimeService, final HistoryService historyService) {
        this.runtimeService = runtimeService;
        this.historyService = historyService;
    }

    @PostMapping("/{rdvId}/Accepted")
    public ResponseEntity<Message> Accepted(@PathVariable("rdvId") Long rdvId) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("rdvId", rdvId);
        final ProcessInstance process = runtimeService.startProcessInstanceByKey("rdvProcess", variables);
        final HistoricVariableInstance hvi = historyService.createHistoricVariableInstanceQuery()
                .processInstanceId(process.getId())
                .variableName("result").singleResult();
        Message message = (Message) hvi.getValue();
        return ResponseEntity.ok(message);


    }


}
