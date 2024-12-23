//package sadiva.mpi.platformbackend.controller.rest;
//
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springdoc.core.annotations.ParameterObject;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.web.PageableDefault;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//import sadiva.mpi.platformbackend.dto.PageResponseDto;
//import sadiva.mpi.platformbackend.dto.user.UserCreateOrUpdateReqDto;
//import sadiva.mpi.platformbackend.dto.user.UserDtoRes;
//import sadiva.mpi.platformbackend.dto.user.UserFilterParam;
//import sadiva.mpi.platformbackend.service.UserService;
//
//import java.util.UUID;
//
//@RestController
//@RequestMapping("api/v1/users")
//@RequiredArgsConstructor
//public class UserController {
//    private final UserService userService;
//
//    @GetMapping
//    public PageResponseDto<UserDtoRes> getPaginated(@ModelAttribute @ParameterObject UserFilterParam filterParam,
//                                                    @PageableDefault(page = 1) @ParameterObject Pageable pageable) {
//        return userService.getPaginated(filterParam, pageable);
//    }
//
//    @GetMapping("/{id}")
//    public UserDtoRes getById(@PathVariable UUID id) {
//        return userService.getById(id);
//    }
//
//    @PostMapping
//    public UserDtoRes save(@RequestBody @Valid UserCreateOrUpdateReqDto userCreateOrUpdateDto) {
//        return userService.save(userCreateOrUpdateDto);
//    }
//
//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteById(@PathVariable UUID id) {
//        userService.deleteById(id);
//    }
//
//    @PutMapping("/{id}")
//    public UserDtoRes update(@PathVariable UUID id, @RequestBody @Valid UserCreateOrUpdateReqDto userCreateOrUpdateDto) {
//        return userService.update(id, userCreateOrUpdateDto);
//    }
//}
