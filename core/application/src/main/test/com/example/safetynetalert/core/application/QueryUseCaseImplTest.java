package com.example.safetynetalert.core.application;

import com.example.safetynetalert.commons.pipelines.query_pipeline.QueryBus;
import com.example.safetynetalert.core.domain.persons.query.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QueryUseCaseImplTest {

    @Mock
    private QueryBus queryBusMock;

    private QueryUseCase queryUseCase;

    @BeforeEach
    void setUp() {
        queryUseCase = new QueryUseCaseImpl(queryBusMock);
    }

    @Test
    void getPersonByFirstnameAndLastname() {
        when(queryBusMock.dispatch(any(GetPersonByFirstnameAndLastnameQuery.class))).thenReturn(Optional.empty());

        var response = queryUseCase.getPersonByFirstnameAndLastname("test", "test");

        assertThat(response).isNotNull();
        assertThat(response).isInstanceOf(Optional.class);
    }

    @Test
    void getPersonsByFirestation() {
        when(queryBusMock.dispatch(any(GetPersonByFireStationQuery.class))).thenReturn(Set.of());

        var response = queryUseCase.getPersonsByFireStation(1);

        assertThat(response).isNotNull();
        assertThat(response).isInstanceOf(Set.class);
    }

    @Test
    void getChildrenByAddressWithFamilyMembers() {
        when(queryBusMock.dispatch(any(GetChildrenByAddressWithFamilyMembersQuery.class))).thenReturn(Set.of());

        var response = queryUseCase.getChildrenByAddressWithFamilyMembers("address");

        assertThat(response).isNotNull();
        assertThat(response).isInstanceOf(Set.class);
    }

    @Test
    void getPersonPhoneNumbersByFirestationNumber() {
        when(queryBusMock.dispatch(any(GetPersonPhoneNumbersByFireStationNumberQuery.class))).thenReturn(Set.of());

        var response = queryUseCase.getPersonPhoneNumbersByFireStationNumber(1);

        assertThat(response).isNotNull();
        assertThat(response).isInstanceOf(Set.class);
    }

    @Test
    void getPersonWithMedicalRecordsByFirestationNumber() {
        when(queryBusMock.dispatch(any(GetPersonsWithMedicalRecordByFireStationNumbersQuery.class))).thenReturn(Set.of());

        var response = queryUseCase.getPersonWithMedicalRecordsByFireStationNumber(Set.of(1));

        assertThat(response).isNotNull();
        assertThat(response).isInstanceOf(Set.class);
    }
}