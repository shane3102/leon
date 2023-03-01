package pl.leon.form.application.leon.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.leon.form.application.leon.mapper.FormMapper;
import pl.leon.form.application.leon.model.request.forms.FormUiUxRankingRequest;
import pl.leon.form.application.leon.repository.FormUiUxRankingRepository;

@Slf4j
@Service
@AllArgsConstructor
public class FormUiUxRankingService {

    private final FormMapper formMapper;
    private final FormUiUxRankingRepository formUiUxRankingRepository;

    public FormUiUxRankingRequest addRanking(FormUiUxRankingRequest ranking) {
        log.debug("addRanking({})", ranking);
        FormUiUxRankingRequest response = formMapper.mapUiUxEntityToResponse(formUiUxRankingRepository.save(formMapper.mapUiUxRankingRequestToEntity(ranking)));
        log.debug("addRanking({}) = {}", ranking, response);
        return response;
    }
}
