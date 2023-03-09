package pl.leon.form.application.leon.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.leon.form.application.leon.mapper.FormMapper;
import pl.leon.form.application.leon.model.both.forms.FormUiUxRanking;
import pl.leon.form.application.leon.model.response.forms.FormUiUxRandomCompletingStatisticsResponse;
import pl.leon.form.application.leon.repository.FormUiUxRankingRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class FormUiUxRankingService {

    private final FormMapper formMapper;
    private final FormUiUxRankingRepository formUiUxRankingRepository;

    public FormUiUxRanking addRanking(FormUiUxRanking ranking) {
        log.debug("addRanking({})", ranking);
        FormUiUxRanking response = formMapper.mapUiUxEntityToResponse(formUiUxRankingRepository.save(formMapper.mapUiUxRankingDtoToEntity(ranking)));
        log.debug("addRanking({}) = {}", ranking, response);
        return response;
    }

    public List<FormUiUxRandomCompletingStatisticsResponse> getRankingsWithPlaceCounts() {
        log.debug("getAllRankings()");
        List<FormUiUxRandomCompletingStatisticsResponse> rankings = formMapper.mapRankingsToResponseWithCounts(formUiUxRankingRepository.findAll());
        log.debug("getAllRankings() = {}", rankings);
        return rankings;
    }

    public List<FormUiUxRanking> getAllRankings() {
        log.debug("getAllRankings()");
        List<FormUiUxRanking> allRankings = formUiUxRankingRepository.findAll().stream().map(formMapper::mapUiUxRankingEntityToDto).collect(Collectors.toList());
        log.debug("getAllRankings() = {}", allRankings.size());
        return allRankings;
    }

}
