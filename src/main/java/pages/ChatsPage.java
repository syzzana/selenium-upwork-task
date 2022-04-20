package pages;

import utils.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ChatsPage extends AbstractPage {

    @FindBy(xpath = "//*[@data-testid='chats-menu-item']")
    private WebElement chatsMenuItem;
    @FindBy(xpath = "//*[@data-tip='Start a conversation']")
    private WebElement startNewConversation;
    @FindBy(xpath = "//*[@placeholder='Type a name or email address']")
    private WebElement typeNameOrEmailOfNewConStarter;
    @FindBy(xpath = "//*[@placeholder='e.g. Team Awesome']")
    private WebElement groupName;
    @FindBy(xpath = "//*[contains(text(), 'Start a conversation')]")
    private WebElement startConversationWindowTitle;
    @FindBy(className = "chat-header")
    private WebElement chatHeader;
    @FindBy(className = "contact-details")
    private WebElement contactDetails;
    @FindBy(className = "text-message")
    private WebElement textMessage;
    @FindBy(className = "DraftEditor-editorContainer")
    private WebElement draftEditorContainer;
    @FindBy(xpath = "//*[@data-testid='message-submit-button']")
    private WebElement messageSubmitButton;
    @FindBy(className = "//*[@data-testid='room-remove-button']")
    private WebElement roomRemoveButton;
    @FindBy(xpath = "//*[@data-contents='true']")
    private WebElement dataContents;
    @FindBy(className = "image-file-message")
    private WebElement imageFileMessage;
    @FindBy(className = "message-list")
    private WebElement messageList;
    @FindBy(className = "message-from")
    private WebElement messageFrom;
    @FindBy(className = "message-date")
    private WebElement messageDate;
    @FindBy(id = "upload-button")
    private WebElement uploadButton;
    @FindBy(xpath = "//*[@data-testid='upload-input']")
    private WebElement uploadInput;
    @FindBy(className = "togo-icon-closes")
    private WebElement iconTogoCloses;
    @FindBy(xpath = "//*[@data-tip='Find or create']")
    private WebElement findOrCreateGroup;
    @FindBy(xpath = "//*[contains(text(),'Search by name or email')]")
    private WebElement searchByNameOrEmail;
    @FindBy(id = "react-select-4-input")
    private WebElement addUserToGrupInput;


    public ChatsPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getChatsMenuItem() {
        return chatsMenuItem;
    }

    public void openChatsPage() {
        waitForElementVisibility(chatsMenuItem,5);
        chatsMenuItem.click();
    }

    public void addNewContactRoom() {
        startNewConversation.click();
        assertThat(startConversationWindowTitle.isDisplayed()).isTrue();
    }

    public void typeNameOrEmailOfNewUser(String emailOrName) {
        typeNameOrEmailOfNewConStarter.sendKeys(emailOrName);
        findElementByText(emailOrName).click();
        waitForElementVisibility(chatHeader,5);
    }


    public void leaveGroup(String groupName) {
        By xPath = By.xpath("//*[@class='rooms-group groups']/*[@class='rooms-list']");
        waitForElementToBeClickable(xPath,15);

        List<WebElement> groupList = driver.findElement(xPath).findElements(By.tagName("a"));
        WebElement group = groupList.stream().filter(groupElement -> groupName.equals(groupElement.getText()))
                .findAny().orElseThrow(IllegalStateException::new);

        group.click();

        waitForElementToBeClickable(By.xpath("//span[contains(text(),'" + groupName + "')]"),10);
        driver.findElement(By.xpath("//*[@data-testid='group-settings']")).click();
        findElementByText("Leave Group").click();
        waitForInvisibilityOfElementLocated(By.xpath("//span[contains(text(),'" + groupName + "')]"),10);
    }

    public boolean isGroupExistent(String group) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By xPath = By.xpath("//*[@class='rooms-group groups']/*[@class='rooms-list']");
        wait.until(ExpectedConditions.elementToBeClickable(xPath));
        List<WebElement> groupList = driver.findElement(xPath).findElements(By.tagName("a"));
        return groupList.stream().anyMatch(groupName -> {
            String name = groupName.getText();
            return name.equals(group);
        });
    }

    public void sendMessage(String message) {
        draftEditorContainer.click();
        dataContents.sendKeys(message);
        messageSubmitButton.click();
    }

    public void openContactMessageRoom(String contactName) {
        findElementByText(contactName).click();
        waitForElementVisibility(chatHeader,5);
        assertThat(contactDetails.getText()).isEqualTo(contactName);
    }


    public boolean isMessageSent(String sentMessage, LocalDateTime sentMessageTime) {
        List<WebElement> messageEntryElements = messageList.findElements((By.xpath("//*[@data-testid='message-history-entry']")));
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("h:mm a");
        String twelveHourFormattedTime = sentMessageTime.format(dateFormat);
        return messageEntryElements.stream().anyMatch(messageEntryElement -> {
            messageFrom = messageEntryElement.findElement(By.className("message-from"));
            messageDate = messageEntryElement.findElement(By.className("message-date"));
            WebElement textMessageElement = messageEntryElement.findElement(By.className("message-body"));

            String sentFrom = messageFrom.getAttribute("textContent");
            String sentTime = messageDate.getAttribute("textContent");
            String textMessage = textMessageElement.getText();

            return Constants.USERNAME.equals(sentFrom) && textMessage.equals(sentMessage) && twelveHourFormattedTime.equals(sentTime);
        });
    }

}
