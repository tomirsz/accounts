package com.accounts.model.response;

import com.accounts.model.dto.AccountDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class GetAccountsResponse {

    private List<AccountDto> accounts;

}
