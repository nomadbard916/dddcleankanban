package ntut.csie.sslab.kanban.usecase.card;


import ntut.csie.sslab.ddd.usecase.cqrs.CqrsCommandOutput;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;
import ntut.csie.sslab.kanban.card.entity.Card;
import ntut.csie.sslab.kanban.card.entity.CardType;
import ntut.csie.sslab.kanban.usecase.AbstractSpringBootJpaTest;
import ntut.csie.sslab.kanban.card.usecase.edit.type.in.ChangeCardTypeInput;
import ntut.csie.sslab.kanban.card.usecase.edit.type.in.ChangeCardTypeUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ChangeCardTypeUseCaseTest extends AbstractSpringBootJpaTest {

    @BeforeEach
    public void setUp() {
        super.setUp();

        workflowId = "workflow id for change card type";
        rootStageId = "root stage id for change card type";
        boardId = "board id for change card type";
        userId = "user id for change card type";
        username = "Teddy";
    }


    @Test
    public void should_succeed_when_change_card_type() {

        String firstCardId = createCard(
                userId,
                workflowId,
                rootStageId,
                "firstCard",
                "s",
                "notes",
                "2020/03/01",
                CardType.General.toString(),
                username,
                boardId);

        String newType = CardType.Expedite.toString();
        ChangeCardTypeUseCase changeCardTypeUseCase = newChangeCardTypeUseCase();

        ChangeCardTypeInput input = new ChangeCardTypeInput();
        input.setCardId(firstCardId);
        input.setNewType(newType);
        input.setUserId(userId);
        input.setUsername(username);
        input.setBoardId(boardId);

        CqrsCommandOutput output = changeCardTypeUseCase.execute(input);

        assertEquals(firstCardId, output.getId());
        assertEquals(ExitCode.SUCCESS,output.getExitCode());
        Card card = cardRepository.findById(output.getId()).get();
        assertEquals(newType, card.getType().toString());
    }

}