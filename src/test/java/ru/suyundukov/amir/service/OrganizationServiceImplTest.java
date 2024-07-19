package ru.suyundukov.amir.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.suyundukov.amir.app.impl.OrganizationServiceImpl;
import ru.suyundukov.amir.entity.Organization;
import ru.suyundukov.amir.exception.ApplicationException;
import ru.suyundukov.amir.adapter.repository.OrganizationRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class OrganizationServiceImplTest {

    @Mock(lenient = true)
    private OrganizationRepository organizationRepository;
    @InjectMocks
    private OrganizationServiceImpl organizationServiceImpl;

    private Organization organization;

    @BeforeEach
    void setUp() {
        organization = new Organization();
        organization.setId(1L);
        organization.setName("John");
        organization.setFizAddress("A avi");
        organization.setLegalAddress("B aviny");

        when(organizationRepository.save(organization)).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
    }

    @Test
    public void testCreateOrganization() {
        Organization createOrganization = organizationServiceImpl.createOrganization(organization);

        assertEquals(organization.getName(), createOrganization.getName());
        assertEquals(organization.getFizAddress(), createOrganization.getFizAddress());
        assertEquals(organization.getLegalAddress(), createOrganization.getLegalAddress());
        assertEquals(organization.getDirector(), createOrganization.getDirector());
        assertEquals(organization.getId(), createOrganization.getId());
        verify(organizationRepository, times(1)).save(organization);
    }

    @Test
    public void testGetOrganizationById() {
        when(organizationRepository.findById(1L)).thenReturn(Optional.of(organization));

        Organization foundOrganization = organizationServiceImpl.getOrganizationById(1L);

        assertEquals(organization.getName(), foundOrganization.getName());
    }

    @Test
    public void testUpdateEmployee() {
        when(organizationRepository.findById(1L)).thenReturn(Optional.of(organization));
        Organization updateOrganization = new Organization();
        updateOrganization.setName("John 1");
        updateOrganization.setFizAddress("A avi 1");
        updateOrganization.setLegalAddress("B aviny 1");

        Organization updatedOrganization = organizationServiceImpl.updateOrganization(1L, updateOrganization);

        assertEquals("John 1", updatedOrganization.getName());//дописать асерты
        assertEquals("A avi 1", updatedOrganization.getFizAddress());//дописать асерты
        assertEquals("B aviny 1", updatedOrganization.getLegalAddress());//дописать асерты
        verify(organizationRepository, times(1)).save(organization);
    }

    @Test
    public void testGetEmployeeById_NotFound() {
        when(organizationRepository.findById(1L)).thenThrow(ApplicationException.class);

        assertThrows(ApplicationException.class, () -> organizationServiceImpl.getOrganizationById(1L));
    }

    @Test
    public void testGetAllEmployee() {
        List<Organization> list = Arrays.asList(organization, organization);
        when(organizationRepository.findAll()).thenReturn(list);

        List<Organization> organizations = organizationServiceImpl.getAllOrganization();

        assertEquals(2, organizations.size());
    }

    @Test
    public void testDeleteEmployee() {
        when(organizationRepository.existsById(1L)).thenReturn(true);
        doNothing().when(organizationRepository).deleteById(1L);

        organizationServiceImpl.deleteOrganization(1L);

        verify(organizationRepository, times(1)).deleteById(1L);
    }

    @Test
    public void delete_NotFound() {
        when(organizationRepository.existsById(1L)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> organizationServiceImpl.deleteOrganization(1L));
    }


}
