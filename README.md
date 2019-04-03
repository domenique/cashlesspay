
# Cashless pay kata.
## Introduction

Welcome, You can call me Mr Po, I’m the domain expert here at MoMoney. We are a company specialised in electronic payments for events and festivals. We 
offer our clients point-of-sale, POS in short, systems which allow their customers to pay using badges, wrist-bands, and soon biometrical chips implanted in their wrists.

In order for this to work, we need to create a software application to manage all of this. The way I see this, there are at least 3 main actors in this system, and all of them need different things.

The cashier should be able to:
* Register new badges/wrist-bands, and provide initial credit on the account.
* Top-up an existing account

The festival junkie should:
* Make an order during the event at one of the ordering boots and pay for them.

The bartender should:
* Have an overview of the outstanding orders.(We don’t want fights at the bar over who was first!)
* Mark an order as prepared so that the junkie can pick up his order.

As you noticed, we at MoMoney are not that special, the whole ordering and payment system strongly resembles how your favourite McDonalds works, the only difference is, we want the drunk people to worry less about there money because we’ve learned that 42% of festival attendees never ask for a refund of the outstanding credit on their accounts at the end of the festival because they are well... too drunk!

## Your assignment

It’s your job to create this application. For this, you are allowed to take all technical decisions: You choose the architecture, the frameworks, the tooling. However, be prepared to explain these decisions, elaborate why you took them and whether you explored alternatives...

Regarding the user interface: guess what… you can also choose whatever you want, be it a console based application, a desktop app, or whatever fancy web framework you choose, even if you want to go mobile, by all means… keep in mind though that we plan to change front ends a lot since all these festivals want to make there own fancy interfaces. so try not to waste too much time on it!

The code should be exactly how you think every enterprise application should be build. Build it as if the next guy that needs to maintain this is a murderous psychopath who knows where you live and who your loved ones are...