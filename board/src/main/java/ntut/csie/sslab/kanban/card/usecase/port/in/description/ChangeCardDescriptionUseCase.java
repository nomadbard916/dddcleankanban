package ntut.csie.sslab.kanban.card.usecase.port.in.description.in;

import ntut.csie.sslab.ddd.usecase.cqrs.Command;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsCommandOutput;

public interface ChangeCardDescriptionUseCase extends Command<ChangeCardDescriptionInput, CqrsCommandOutput> {
}