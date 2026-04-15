---
layout: page
title: PetLog User Guide
---

## Welcome to PetLog

**Your pets are in good hands. Your records should be too.**

Running a pet daycare or boarding service means juggling a lot — which owner is picking up Biscuit today? Does Mochi have any dietary notes? What's the contact number for Luna's owner again?

PetLog is a **desktop app** that keeps all of this organised in one place, so you can spend less time searching through notebooks and chat messages, and more time with the animals in your care.

It works through simple typed commands — no clicking through menus, no complicated software to learn. If you can type, you're ready to use PetLog.

--------------------------------------------------------------------------------------------------------------------

## What PetLog Can Do For You

-  **Store owner contacts** — name, phone, email, and address, all in one record
-  **Track pets and their owners** — link each pet to their owner, with species and special care notes
-  **Find anyone instantly** — search by owner name, email, pet name, species, or tag
-  **Update care notes on the fly** — record feeding instructions, medical reminders, or behavioural notes
-  **Manage services and sessions** — add the services you offer and track specific sessions for each pet
-  **Keep your records clean** — remove owners and pets when they leave your service

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## Who Is This Guide For?

PetLog is built for pet daycare and boarding service staff who want a fast, no-fuss way to manage their contacts and pet records.

We assume:
- You're comfortable typing on a keyboard
- You have no coding or technical background — and you don't need one
- You're using a Windows, Mac, or Linux computer

--------------------------------------------------------------------------------------------------------------------

## How to Read This Guide

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
Important — read before you act.
</div>

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Extra context to help you understand.
</div>

`monospace text` — Something you type into PetLog or terminals exactly as shown.

--------------------------------------------------------------------------------------------------------------------

## Where to Type Commands

All commands in PetLog should be written in the area highlighted by the red box shown in the picture below:

![Commands.png](images/Commands.png)

<div style="page-break-after: always;"></div>"

## Table of Contents
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## Quick start

### Step 1 — Make sure Java is installed.

PetLog runs on something called Java — a free program that lets apps like PetLog work on any computer, whether it's Windows, Mac, or Linux. Think of it like a universal engine under the hood. You only need to install it once, and many computers already have it.

To check, open your Terminal (Mac or Linux) or Command Prompt (Windows) and type:

```
java -version
```

If you see a version number of **17 or higher** — great, move on to Step 2!

If not, install it using the guide for your system:
- [Windows: Java installation guide for Windows](https://se-education.org/guides/tutorials/javaInstallationWindows.html)
- [Mac: Java installation guide for Mac](https://se-education.org/guides/tutorials/javaInstallationMac.html)
- [Linux: Java installation guide for Linux](https://se-education.org/guides/tutorials/javaInstallationLinux.html)

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Not sure how to open Terminal or Command Prompt? On Windows, press the Windows key, type `cmd` and hit Enter. On Mac, press `Cmd + Space`, type `Terminal` and hit Enter.
</div>

### Step 2 — Download PetLog.

Download the latest `petlog.jar` file from the [releases page](https://github.com/AY2526S2-CS2103T-W14-1/tp/releases). Look for the **Assets** section and save the file somewhere easy to find, like your Desktop or a folder called `PetLog`.

### Step 3 — Launch PetLog.

Open your Terminal or Command Prompt, navigate to the folder where you saved PetLog by typing:

```
cd YOUR_FOLDER_PATH
```

For example, if you saved it in a folder called `PetLog` on your Desktop:

```
cd Desktop/PetLog
```
<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
`cd` stands for "change directory" — it's just your way of telling the computer which folder to look in. Think of it like opening a folder by typing its name instead of clicking on it.
</div>

Then launch PetLog by typing:

```
java -jar petlog.jar
```

PetLog will open in a few seconds with some sample data loaded. Don't worry! You can change or clear this data later. 
   
![Ui](images/Ui.png)

### Step 4 — Try your first commands.

PetLog loads with some sample data so you can explore right away. Type any of these into the command box and press Enter:

   * [`list`](#listing-all-owners-list) : Lists all owners, pets, and sessions.

   * [`addowner on/John Doe ph/98765432 em/johnd@example.com ad/John street, block 123, #01-01`](#adding-an-owner-addowner) : Adds an owner.

   * [`addpet oi/1 pn/Molly ps/Golden Retriever pr/cuddly`](#adding-a-pet-under-an-owner-addpet) : Adds a pet.

   * [`addservice sn/Nail trim sp/10.00`](#adding-a-service-addservice) : Adds a new available service.

   * [`addsession oi/1 pi/1 st/2026-04-06 10:00 et/2026-04-06 11:00 sn/Nail trim`](#adding-a-session-addsession) : Adds a session for a pet.

   * [`clear`](#clearing-all-owners-pets-services-and-sessions-clear) : Clears all owners, pets, services, and sessions.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
`clear` permanently deletes everything. There is no undo. Use with care!
</div>

   * [`exit`](#exiting-the-program-exit) : Exits the app.

Refer to the [Features](#features) below for details of each command.

[↑ Back to ToC](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in [`addowner on/OWNER_NAME`](#adding-an-owner-addowner), `OWNER_NAME` is a parameter which can be used as [`addowner on/John Doe`](#adding-an-owner-addowner).

* Items in square brackets are optional.<br>
  e.g. `on/OWNER_NAME [ot/TAG]` can be used as `on/John Doe ot/priority` or as `on/John Doe`.

* Items with `…​` after them can be used multiple times including zero times.<br>
  e.g. `[ot/TAG]…​` can be used as ` ` (i.e. 0 times), `ot/friend`, `ot/friend ot/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `on/OWNER_NAME ph/PHONE_NUMBER`, `ph/PHONE_NUMBER on/OWNER_NAME` is also acceptable.

* Command words and prefixes are case-insensitive.<br>
  e.g. [`AdDoWnEr on/John Doe Ph/98765432 eM/j@example.com AD/123, Street`](#adding-an-owner-addowner) is accepted.

* Extraneous parameters for commands that do not take in parameters (such as [`help`](#viewing-help-help), [`list`](#listing-all-owners-list), [`exit`](#exiting-the-program-exit) and [`clear`](#clearing-all-owners-pets-services-and-sessions-clear)) will be ignored.<br>
  e.g. if the command specifies [`help 123`](#viewing-help-help), it will be interpreted as [`help`](#viewing-help-help).

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

<div style="page-break-after: always;"></div>

### Viewing help: `help`

Shows a message explaining how to use the available commands and how to access the user guide:

![help message](images/helpMessage.png)

Format: `help`

[↑ Back to ToC](#table-of-contents)

<div style="page-break-after: always;"></div>

### Adding an owner: `addowner`

Adds an owner to PetLog. An owner has a name, a phone number, an email, an address, and any number of tags.

Format: `addowner on/OWNER_NAME ph/PHONE_NUMBER em/EMAIL ad/ADDRESS [ot/TAG]…​`

* `OWNER_NAME` must be 1 to 50 characters.
* `PHONE_NUMBER` must be 1 to 30 characters.
* If `PHONE_NUMBER` contains any non-numeric characters, the command succeeds but shows a warning in case it was not intentional.
* `EMAIL` must be of the form `local-part@domain`.
  The `local-part` must be 1 to 30 characters long. It may contain alphanumeric characters and these special characters: ``!#$%&'*+-=?^_`{|}~/.``. It must start and end with an alphanumeric character, and it cannot contain consecutive periods (`..`).
  The `domain` must be 2 to 30 characters long. Each domain label must start and end with an alphanumeric character, and may contain hyphens only in the middle. The final domain label must be at least 2 characters long.
* `ADDRESS` must be 1 to 100 characters.
* Each `TAG`, if provided, must be 1 to 20 characters.
* Attempting to add a duplicate owner, if both their name and phone number match an existing owner, will not succeed.
* Upon successful addition of an owner, PetLog will display all owners (as if [`list`](#listing-all-owners-list) was run) and automatically scroll to the bottom of the list for you to see the newly added owner.

Examples:
* `addowner on/John Doe ph/98765432 em/johnd@example.com ad/John street, block 123, #01-01`
* `addowner on/Betsy Crowe ot/friend em/betsycrowe@example.com ad/Newgate Prison ph/1234567 ot/criminal`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
You can use any types of characters for `PHONE_NUMBER`. Want to indicate that this new owner's phone number is the `Same as Alice's`? Or perhaps use `-` to represent that they don't have a phone number? PetLog is flexible to all these possibilities!
</div>

[↑ Back to ToC](#table-of-contents)

<div style="page-break-after: always;"></div>

### Editing an owner: `editowner`

Edits an existing owner in PetLog.

Format: `editowner oi/OWNER_INDEX [on/OWNER_NAME] [ph/PHONE_NUMBER] [em/EMAIL] [ad/ADDRESS] [ot/OVERWRITE_TAG]…​ [at/ADD_TAG]…​ [rt/REMOVE_TAG]…​`

* `OWNER_INDEX` refers to the index number shown in the displayed owner list. It must be a positive integer.
* At least one of the optional fields must be provided, following the same input validation rules as in [`addowner`](#adding-an-owner-addowner).
* Existing values, except tags, will be updated to the input values.
* To add to existing tags, use `at/`.
* To remove existing tags, use `rt/`.
* To overwrite the existing tags, use `ot/`.
* You can remove all the owner's tags by typing `ot/` without specifying any tags after it.
* `ot/` cannot be used with `at/` or `rt/`.

Examples:
*  `editowner oi/1 ph/91234567 em/johndoe@example.com` edits the phone number and email address of the 1st owner to be `91234567` and `johndoe@example.com` respectively.
*  `editowner oi/3 rt/member at/VIP` removes the (assumed existing) `member` tag and adds a `VIP` tag to the 3rd owner.
*  `editowner oi/2 on/Betsy Crower ot/` edits the name of the 2nd owner to be `Betsy Crower` and clears all existing tags.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
Having one invalid input will cause your entire command to fail! For example: Assuming the 1st owner does not have a `member` tag, running `editowner oi/1 at/VIP rt/member` will fail entirely, and hence a `VIP` tag will **not** be added to that owner.
</div>

[↑ Back to ToC](#table-of-contents)

<div style="page-break-after: always;"></div>

### Adding a pet under an owner: `addpet`

Adds a pet belonging to an existing owner in PetLog. A pet has an owner, a name, a species, and may have remarks.

Format: `addpet oi/OWNER_INDEX pn/PET_NAME ps/SPECIES [pr/REMARKS]`

* `OWNER_INDEX` refers to the index number shown in the displayed owner list. It must be a positive integer.
* `PET_NAME` must be 1 to 30 characters.
* `SPECIES` must be 1 to 30 characters.
* `REMARKS`, if provided, must be 1 to 100 characters. Simply inputting `pr/` with no remarks provided will add the pet with no remarks.
* Attempting to add a duplicate pet, if both its name and species match an existing pet for the specified owner, will not succeed.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
A pet's owner, name and species cannot be edited once it has been added. Make sure they are all correct before adding sessions to the pet!
If not, you can still [`delete`](#deleting-an-owner-pet-session-or-service-delete) the pet and re-add it with the corrected details.
</div>

Examples:
* `addpet oi/2 pn/Molly ps/Golden Retriever pr/cuddly` adds a Golden Retriever called Molly under the 2nd owner in the list of owners; Molly will have a remark that she is cuddly.
* `addpet oi/1 pn/Dave ps/Great Dane` adds a Great Dane called Dave under the 1st owner on the list of owners.

[↑ Back to ToC](#table-of-contents)

<div style="page-break-after: always;"></div>

### Updating the remarks of a pet: `update`

Format: `update oi/OWNER_INDEX pi/PET_INDEX pr/REMARKS`

Updates the remarks of a pet.

* `OWNER_INDEX` refers to the index number shown in the displayed owner list. It must be a positive integer.
* `PET_INDEX` refers to the index number shown in the displayed pet list of the specified owner. It must be a positive integer.
* Existing remarks will be overwritten and updated to the provided input.

Examples:
* `update oi/1 pi/3 pr/aggressive` updates the remark of the 3rd pet listed under the 1st owner to be "aggressive".

[↑ Back to ToC](#table-of-contents)

<div style="page-break-after: always;"></div>

### Searching for owners: `find`

Displays owners whose details match at least one of the given keywords.

Format: `find [on/OWNER_NAME] [ph/PHONE_NUMBER] [em/EMAIL] [ad/ADDRESS] [ot/OWNER_TAG]…​ [pn/PET_NAME] [ps/SPECIES] [pr/REMARKS]`

* At least one of the optional fields must be provided.
* The search is case-insensitive. e.g. `hans` will match `Hans`.
* Partial matches are displayed. e.g. `Han` will match `Hans`.
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`.
* Owners matching at least one keyword will be returned (i.e. `OR` search). e.g. `Hans Bo` will match both `Hans Gruber` and `Bo Yang`.

Examples:
* `find ps/Dog` returns owners who own pets that are `Dog`s.
* `find on/avi jon` returns owners whose names contain `avi` OR `jon`, e.g. `Avi`, `Xavier`, `Jonathan`.
* `find ad/Tampines ot/VIP` returns owners whose address contains `Tampines` OR who are tagged as `VIP`s _(screenshot cropped to show relevant UI elements)_:
![result for 'find ad/Tampines ot/VIP'](images/findAdTampinesOtVip.png)

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Use [`list`](#listing-all-owners-list) after using `find` to return to the list of all owners. <br>
Successfully [adding an owner](#adding-an-owner-addowner) will also reset the view and display all owners.
</div>

[↑ Back to ToC](#table-of-contents)

<div style="page-break-after: always;"></div>

### Listing all owners: `list`

Shows all owners, pets and sessions in PetLog.

Format: `list`

[↑ Back to ToC](#table-of-contents)

### Adding a service: `addservice`

Adds a service to the service catalogue. A service has a name and a price.

Format: `addservice sn/SERVICE_NAME sp/SERVICE_PRICE`

* `SERVICE_NAME` must be 1 to 30 characters.
* `SERVICE_PRICE` is in dollars, and must be a number from `0` to `10000` (inclusive), with up to 2 decimal places.
* Attempting to add a duplicate service, if its name matches an existing service in the service catalogue, will not succeed.

Examples:
* `addservice sn/Ear Cleaning sp/12.50` adds Ear Cleaning as a service to the list with the price of $12.50.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
To change a service's name or price after it has been added, simply [`delete`](#deleting-an-owner-pet-session-or-service-delete) it and re-add it.
</div>

[↑ Back to ToC](#table-of-contents)

<div style="page-break-after: always;"></div>

### Adding a session: `addsession`

Adds a session for the specified pet. A session has a pet, a start time, an end time, and a list of services, from which the fee is calculated.

Format: `addsession oi/OWNER_INDEX pi/PET_INDEX st/START_TIME et/END_TIME [sn/SERVICE_NAME]…​`

* `OWNER_INDEX` refers to the index number shown in the displayed owner list. It must be a positive integer.
* `PET_INDEX` refers to the index number shown in the displayed pet list of the specified owner. It must be a positive integer.
* `START_TIME` and `END_TIME` must be of the format `yyyy-MM-dd HH:mm`.
* `END_TIME` must be chronologically after `START_TIME`.
* `SERVICE_NAME`, if provided, must match an existing service in the service catalogue.
* Attempting to add a session whose timing overlaps with an existing session for the specified pet will not succeed.
* Sessions for each pet are automatically sorted by `START_TIME` (earliest to latest) after each successful `addsession`.
* The session panel is refreshed after every successful command, so owner/pet/session indices shown in the UI always match the current displayed owner list (e.g. after `find`, `list`, `addsession`, `delete`, etc.).

Examples:
* `addsession oi/1 pi/2 st/2026-05-15 14:30 et/2026-05-15 15:30 sn/Base service charge sn/Shampoo` adds a session for the 2nd pet listed under the 1st owner; it is from 2:30pm to 3:30pm on 15 May 2026; its list of services are `Base service charge` and `Shampoo`.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
You may add more than one instance of the same service to a single session! This may be useful for longer sessions at care centres that span over several days, where you may need to walk a pet every day, for example. <br>
You may also add sessions that have happened in the past, be it for bookkeeping, tracking clients' loyalty, or simply just for nostalgia!
</div>

[↑ Back to ToC](#table-of-contents)

<div style="page-break-after: always;"></div>

### Deleting an owner, pet, session or service: `delete`

`delete` has two usages, each with its own format.

**1. Deleting an owner, pet or session**

Deletes the specified owner, pet or session from PetLog.

Format: `delete oi/OWNER_INDEX [pi/PET_INDEX [si/SESSION_INDEX]]`

* `OWNER_INDEX` refers to the index number shown in the displayed owner list. It must be a positive integer.
* `PET_INDEX` refers to the index number shown in the displayed pet list of the specified owner. It must be a positive integer.
* `SESSION_INDEX` refers to the index number shown in the displayed session list of the specified pet. It must be a positive integer.
* `SESSION_INDEX` values are based on the current displayed lists and can change after commands that alter visible owners/pets/sessions (e.g. `find`, `list`, `addsession`, `delete`).
* Using `delete` with the `oi/` prefix only deletes the owner at `OWNER_INDEX`.
* Using `delete` with the `oi/` and `pi/` prefixes only deletes the pet at `PET_INDEX` of that owner.
* Using `delete` with the `oi/`, `pi/` and `si/` prefixes deletes the session at `SESSION_INDEX` of that pet.

Examples:
* `delete oi/4` deletes the 4th owner listed.
* `delete oi/4 pi/2` deletes the 2nd pet listed of the 4th owner.
* `delete oi/4 pi/2 si/3` deletes the 3rd session listed of the 2nd pet of the 4th owner.
* [`list`](#listing-all-owners-list) followed by `delete oi/2` deletes the 2nd owner listed.
* [`find on/Betsy`](#searching-for-owners-find) followed by `delete oi/1 pi/2` deletes the 2nd pet of the 1st owner in the results of the [`find`](#searching-for-owners-find) command.

<div style="page-break-after: always;"></div>

**2. Deleting a service**

Deletes a service from the service catalogue.

Format: `delete sn/SERVICE_NAME`

* `SERVICE_NAME` must match an existing service in the service catalogue.

Examples:
* `delete sn/Ear Cleaning` deletes Ear Cleaning as a service from the list (if it exists).

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
Deleting a service will not delete the sessions that use that service! If you do so, a warning message detailing the sessions that still use the deleted service will be shown.
</div>

<div markdown="block" class="alert alert-info">

**:information_source: Note about the `delete` formats:**<br>

Using a combination of both formats, e.g. `delete oi/1 sn/Ear Cleaning`, is invalid and will not succeed.

</div>

[↑ Back to ToC](#table-of-contents)

### Clearing all owners, pets, services and sessions: `clear`

Deletes all owners, pets, services and sessions from PetLog.

Format: `clear`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Use `clear` to remove the sample data when you first run PetLog so you can start putting in your own!
</div>

[↑ Back to ToC](#table-of-contents)

### Exiting the program: `exit`

Exits PetLog.

Format: `exit`

[↑ Back to ToC](#table-of-contents)

<div style="page-break-after: always;"></div>

### Saving the data

PetLog data is saved to the hard disk automatically after any command that changes the data, and upon exiting. There is no need to save manually.

[↑ Back to ToC](#table-of-contents)

### Editing the data file

PetLog data is saved automatically as a JSON file: <br> `[JAR file location]/data/petlog.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file make its format invalid, PetLog will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause PetLog to behave in unexpected ways (e.g., if a value entered is outside of the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</div>

[↑ Back to ToC](#table-of-contents)

### Undo/Redo `[Coming Soon]`

_Details coming soon ..._

[↑ Back to ToC](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## FAQ

**Q**: How do I transfer my PetLog data to another computer?<br>
**A**: Install PetLog on the other computer, and overwrite the empty data file it creates with the file that contains the data from your previous PetLog home folder.

**Q**: Why do indexes become invalid after I run [`find`](#searching-for-owners-find)?<br>
**A**: Indexes always refer to the current displayed list. After filtering, either use the new filtered indexes or run [`list`](#listing-all-owners-list) to reset the indexes before deleting or updating.

**Q**: How do I clear a pet’s remark?<br>
**A**: Use an empty remark value: [`update oi/OWNER_INDEX pi/PET_INDEX pr/`](#updating-the-remarks-of-a-pet-update).

**Q**: Can I create a session without services?<br>
**A**: Yes. `sn/` is optional in [`addsession`](#adding-a-session-addsession). If no services are provided, the session fee is `0.00`.

**Q**: How is a session’s total fee calculated?<br>
**A**: It is the sum of all services provided in the [`addsession`](#adding-a-session-addsession) command, based on service prices at the time the session is created.

**Q**: Why does [`addsession`](#adding-a-session-addsession) fail with “Unknown service”?<br>
**A**: At least one `sn/SERVICE_NAME` does not exist in your current service list. Add it first with [`addservice`](#adding-a-service-addservice), or correct the name.

**Q**: Where is my data stored, and how do I reset to sample data?<br>
**A**: Data is stored at `[JAR location]/data/petlog.json`. Back up that file to migrate data. To reset to sample data, delete `petlog.json` and restart the app.

**Q**: I used [`editowner`](#editing-an-owner-editowner)/[`update`](#updating-the-remarks-of-a-pet-update) after [`find`](#searching-for-owners-find), and the owner disappeared from the list!<br>
**A**: This is because the change you made resulted in the removal of that owner from the filtered list presented by [`find`](#searching-for-owners-find). Simply use [`list`](#listing-all-owners-list) to display all owners again!

[↑ Back to ToC](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

1. **If you minimise the Help Window** and then run the [`help`](#viewing-help-help) command (or use the `Help` menu, or the keyboard shortcut `F1`) again, the original Help Window will remain minimised, and no new Help Window will appear. The remedy is to manually restore the minimised Help Window.

1. **On macOS, if the Help Window is closed while it is in fullscreen**, the application may become unresponsive. The remedy is to type `Control` + `C` in your Terminal window to close the application, and then re-launch it with `java -jar petlog.jar`. For this reason, it is best to avoid closing the Help Window when it is in fullscreen.

1. **If `petlog.jar` is placed in a write-protected folder**, the application will not work as it requires write permissions to write and update the local storage file `petlog.json`. The remedy is to ensure `petlog.jar` is placed in a folder with no write protection before using it.

[↑ Back to ToC](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always;"></div>

## Command summary

Action | Format, Examples
--------|------------------
[**Help**](#viewing-help-help) | `help`
[**Add Owner**](#adding-an-owner-addowner) | `addowner on/OWNER_NAME ph/PHONE_NUMBER em/EMAIL ad/ADDRESS [ot/TAG]…​` <br> e.g., `addowner on/John Doe ph/98765432 em/johnd@example.com ad/John street, block 123, #01-01`
[**Edit Owner**](#editing-an-owner-editowner) | `editowner oi/OWNER_INDEX [on/OWNER_NAME] [ph/PHONE_NUMBER] [em/EMAIL] [ad/ADDRESS] [ot/OVERWRITE_TAG]…​ [at/ADD_TAG]…​ [rt/REMOVE_TAG]…​`<br> e.g., `editowner oi/1 ph/91234567 em/johndoe@example.com`
[**Add Pet**](#adding-a-pet-under-an-owner-addpet) | `addpet oi/OWNER_INDEX pn/PET_NAME ps/SPECIES [pr/REMARKS]` <br> e.g., `addpet oi/2 pn/Molly ps/Golden Retriever pr/cuddly`
[**Update Pet Remarks**](#updating-the-remarks-of-a-pet-update) | `update oi/OWNER_INDEX pi/PET_INDEX pr/REMARKS` <br> e.g., `update oi/1 pi/3 pr/aggressive`
[**Search for Owners**](#searching-for-owners-find) | `find [on/OWNER_NAME] [ph/PHONE_NUMBER] [em/EMAIL] [ad/ADDRESS] [ot/OWNER_TAG]…​ [pn/PET_NAME] [ps/SPECIES] [pr/REMARKS]`<br> e.g., `find on/Hans ps/Dog`
[**List All Owners**](#listing-all-owners-list) | `list`
[**Add Service**](#adding-a-service-addservice) | `addservice sn/SERVICE_NAME sp/SERVICE_PRICE` <br> e.g., `addservice sn/Ear Cleaning sp/12.50`
[**Add Session**](#adding-a-session-addsession) | `addsession oi/OWNER_INDEX pi/PET_INDEX st/START_TIME et/END_TIME [sn/SERVICE_NAME]…​` <br> e.g., `addsession oi/1 pi/2 st/2026-05-15 14:30 et/2026-05-15 15:30 sn/Base service charge sn/Shampoo`
[**Delete Owner, Pet or Session**](#deleting-an-owner-pet-session-or-service-delete) | `delete oi/OWNER_INDEX [pi/PET_INDEX [si/SESSION_INDEX]]`<br> e.g., `delete oi/4 pi/2`
[**Delete Service**](#deleting-an-owner-pet-session-or-service-delete) | `delete sn/SERVICE_NAME` <br> e.g., `delete sn/Ear Cleaning`
[**Clear All Entries**](#clearing-all-owners-pets-services-and-sessions-clear) | `clear`
[**Exit Application**](#exiting-the-program-exit) | `exit`

[↑ Back to ToC](#table-of-contents)

--------------------------------------------------------------------------------------------------------------------
