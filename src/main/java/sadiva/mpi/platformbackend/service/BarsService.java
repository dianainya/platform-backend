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
import sadiva.mpi.platformbackend.service.exception.PrisonerNotFoundException;
import sadiva.mpi.platformbackend.service.exception.ViolationNotFoundException;

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
        int rowAffected = ratingRepo.changeRating(req.personId(), Math.abs(req.score()));
        if (rowAffected == 0) {
            throw new PrisonerNotFoundException(req.personId());
        }
    }

    @Transactional
    public void subtractScore(BarsSubtractScoreReq req) {
        Integer score = violationRepo.getScoreByCode(req.violationCode());
        if (score == null){
            throw new ViolationNotFoundException(req.violationCode());
        }
        int rowAffected = ratingRepo.changeRating(req.personId(), -score);
        if (rowAffected == 0) {
            throw new PrisonerNotFoundException(req.personId());
        }
    }
}
