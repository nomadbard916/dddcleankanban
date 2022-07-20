package ntut.csie.sslab.kanban.card.usecase.edit.note.impl;

import ntut.csie.sslab.ddd.usecase.DomainEventBus;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsCommandOutput;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;
import ntut.csie.sslab.kanban.card.entity.Card;
import ntut.csie.sslab.kanban.common.ClientBoardContentMightExpire;
import ntut.csie.sslab.kanban.card.usecase.port.out.CardRepository;
import ntut.csie.sslab.kanban.card.usecase.edit.note.in.ChangeCardNoteInput;
import ntut.csie.sslab.kanban.card.usecase.edit.note.in.ChangeCardNoteUseCase;

public class ChangeCardNoteUseCaseImpl implements ChangeCardNoteUseCase {

    private final CardRepository cardRepository;
    private final DomainEventBus domainEventBus;

    public ChangeCardNoteUseCaseImpl(CardRepository cardRepository, DomainEventBus domainEventBus) {
        this.cardRepository = cardRepository;
        this.domainEventBus = domainEventBus;
    }

    @Override
    public CqrsCommandOutput execute(ChangeCardNoteInput input) {
        Card card = cardRepository.findById(input.getCardId()).orElse(null);

        CqrsCommandOutput output = new CqrsCommandOutput();

        if (null == card){
            output.setId(input.getCardId())
                    .setExitCode(ExitCode.FAILURE)
                    .setMessage("Change card estimate failed: card not found, card id = " + input.getCardId());
            domainEventBus.post(new ClientBoardContentMightExpire(input.getBoardId()));
            return output;
        }

        card.changeNotes(input.getNewNotes(), input.getUserId(), input.getUsername(), input.getBoardId());

        cardRepository.save(card);
        domainEventBus.postAll(card);

        return output.setId(card.getCardId()).setExitCode(ExitCode.SUCCESS);
    }

}
