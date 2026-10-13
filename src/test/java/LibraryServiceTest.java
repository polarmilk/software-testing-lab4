package Library;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LibraryServiceTest {

    @Mock
    private EmailProvider emailProvider;

    @Mock
    private ResourceRepository resourceRepository;

    private LibraryService libraryService;

    @BeforeEach
    void setUp() {
        libraryService = new LibraryService(emailProvider, resourceRepository);
    }

    // TC1: Null resource ID
    @Test
    void checkoutResource_nullResourceId_returnsFalse() throws Exception {
        boolean result = libraryService.checkoutResource(
                null, "member@example.com");

        assertFalse(result);

        verifyNoInteractions(resourceRepository, emailProvider);
    }

    // TC2: Resource unavailable
    @Test
    void checkoutResource_resourceUnavailable_returnsFalse() throws Exception {
        UUID resourceId = UUID.randomUUID();

        when(resourceRepository.isResourceAvailable(resourceId))
                .thenReturn(false);

        boolean result = libraryService.checkoutResource(
                resourceId, "member@example.com");

        assertFalse(result);

        verify(resourceRepository).isResourceAvailable(resourceId);
        verify(resourceRepository, never()).updateStatus(any(), anyBoolean());
        verifyNoInteractions(emailProvider);
    }

    // TC3: Status update fails
    @Test
    void checkoutResource_statusUpdateFails_throwsDatabaseFailureException() throws Exception{
        UUID resourceId = UUID.randomUUID();

        when(resourceRepository.isResourceAvailable(resourceId))
                .thenReturn(true);
        when(resourceRepository.updateStatus(resourceId, false))
                .thenReturn(false);

        assertThrows(
                DatabaseFailureException.class,
                () -> libraryService.checkoutResource(
                        resourceId, "member@example.com"));

        verify(resourceRepository).isResourceAvailable(resourceId);
        verify(resourceRepository).updateStatus(resourceId, false);
        verifyNoInteractions(emailProvider);
    }

    // TC4: Email sending fails
    @Test
    void checkoutResource_emailFails_throwsEmailFailureException() throws Exception{
        UUID resourceId = UUID.randomUUID();

        when(resourceRepository.isResourceAvailable(resourceId))
                .thenReturn(true);
        when(resourceRepository.updateStatus(resourceId, false))
                .thenReturn(true);
        when(emailProvider.sendEmail(
                eq("member@example.com"),
                anyString()))
                .thenReturn(false);

        assertThrows(
                EmailFailureException.class,
                () -> libraryService.checkoutResource(
                        resourceId, "member@example.com"));

        verify(resourceRepository).isResourceAvailable(resourceId);
        verify(resourceRepository).updateStatus(resourceId, false);
        verify(emailProvider).sendEmail(
                eq("member@example.com"), anyString());
    }

    // TC5: Checkout succeeds
    @Test
    void checkoutResource_allOperationsSucceed_returnsTrue() throws Exception {
        UUID resourceId = UUID.randomUUID();

        when(resourceRepository.isResourceAvailable(resourceId))
                .thenReturn(true);
        when(resourceRepository.updateStatus(resourceId, false))
                .thenReturn(true);
        when(emailProvider.sendEmail(
                eq("member@example.com"),
                anyString()))
                .thenReturn(true);

        boolean result = libraryService.checkoutResource(
                resourceId, "member@example.com");

        assertTrue(result);

        verify(resourceRepository).isResourceAvailable(resourceId);
        verify(resourceRepository).updateStatus(resourceId, false);
        verify(emailProvider).sendEmail(
                eq("member@example.com"), anyString());
    }
}
