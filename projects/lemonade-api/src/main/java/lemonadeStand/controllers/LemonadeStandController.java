package lemonadeStand.controllers;

import lemonadeStand.model.LemonadeStandRequestDto;
import lemonadeStand.model.LemonadeStandResponseDto;
import lemonadeStand.services.LemonadeStandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("lemonadestands")
@RequiredArgsConstructor
public class LemonadeStandController {

    private final LemonadeStandService lemonadeStandService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LemonadeStandResponseDto createLemonadeStand(@RequestBody LemonadeStandRequestDto lemonadeStandRequestDto) {
        return lemonadeStandService.createLemonadeStand(lemonadeStandRequestDto);
    }

    @GetMapping
    public List<LemonadeStandResponseDto> getAllLemonadeStands() {
        return lemonadeStandService.getAllLemonadeStands();
    }

    @GetMapping("/{id}")
    public LemonadeStandResponseDto getLemonadeStandById(@PathVariable Long id) {
        return lemonadeStandService.getLemonadeStandById(id);
    }

    @PatchMapping("/{id}")
    public LemonadeStandResponseDto updateLemonadeStand(@PathVariable Long id,
                                                        @RequestBody LemonadeStandRequestDto lemonadeStandRequestDto) {
        return lemonadeStandService.updateLemonadeStand(id, lemonadeStandRequestDto);
    }

    @DeleteMapping("/{id}")
    public LemonadeStandResponseDto deleteLemonadeStand(@PathVariable Long id) {
        return lemonadeStandService.deleteLemonadeStand(id);
    }

}
