package sadiva.mpi.platformbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sadiva.mpi.platformbackend.dto.bars.BarsAddScoreReq;
import sadiva.mpi.platformbackend.dto.bars.BarsSubtractScoreReq;
import sadiva.mpi.platformbackend.dto.bars.ViolationRes;
import sadiva.mpi.platformbackend.mapper.BarsMapper;
import sadiva.mpi.platformbackend.repo.RatingRepo;
import sadiva.mpi.platformbackend.repo.ViolationRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BarsService {
    private final ViolationRepo violationRepo;
    private final RatingRepo ratingRepo;
    private final BarsMapper barsMapper;

    public List<ViolationRes> getAllViolations() {
        return barsMapper.violationToDtoFromEntity(violationRepo.getAllViolations());
    }

    public void addScore(BarsAddScoreReq req) {
        ratingRepo.changeRating(req.personId(), Math.abs(req.score()));
    }

    @Transactional
    public void subtractScore(BarsSubtractScoreReq req) {
        Integer score = violationRepo.getScoreByCode(req.violationCode());
        ratingRepo.changeRating(req.personId(), -score);
    }
}
