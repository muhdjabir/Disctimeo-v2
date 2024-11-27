import { Card, CardContent, CardFooter, CardHeader, CardTitle } from "@/components/ui/card"
import { Button } from "@/components/ui/button"
import { CalendarIcon, Users } from 'lucide-react'
import { Event } from "@/types/event"
import { Badge } from "@/components/ui/badge"
import {
    Dialog,
    DialogContent,
    DialogDescription,
    DialogFooter,
    DialogHeader,
    DialogTitle,
    DialogTrigger,
} from "@/components/ui/dialog"
import { useState } from "react"

interface EventCardProps {
    event: Event
    onRegister: (eventId: string) => void
}

export function EventCard({ event, onRegister }: EventCardProps) {
    const [showConfirmDialog, setShowConfirmDialog] = useState(false)

    const handleRegister = () => {
        setShowConfirmDialog(false)
        onRegister(event.id)
    }

    return (
        <Card className="flex flex-col h-full">
            <CardHeader>
                <div className="flex justify-between items-center">
                    <div>
                        <CardTitle className="mb-1">{event.name}</CardTitle>
                        <Badge variant="secondary">{event.type}</Badge>
                    </div>
                    <Badge variant="outline" className="whitespace-nowrap">
                        <CalendarIcon className="mr-2 h-4 w-4" />
                        {event.date.toLocaleDateString()}
                    </Badge>
                </div>
            </CardHeader>
            <CardContent className="flex-grow">
                <p className="text-muted-foreground mb-4">{event.description}</p>
                <p className="text-sm">
                    <strong>Registration:</strong> {event.registrationDetails}
                </p>
                {event.creatorTeam && (
                    <p className="text-sm mt-2">
                        <strong>Organized by:</strong> {event.creatorTeam.name}
                    </p>
                )}
            </CardContent>
            <CardFooter className="flex justify-between">
                <Dialog>
                    <DialogTrigger asChild>
                        <Button variant="outline">
                            <Users className="mr-2 h-4 w-4" />
                            View Participants
                        </Button>
                    </DialogTrigger>
                    <DialogContent>
                        <DialogHeader>
                            <DialogTitle>Event Participants</DialogTitle>
                        </DialogHeader>
                        <div className="grid gap-4">
                            {event.participants.map((participant) => (
                                <div key={participant.id} className="flex items-center space-x-4">
                                    <div className="rounded-full bg-muted w-10 h-10 flex items-center justify-center">
                                        {participant.name.charAt(0)}
                                    </div>
                                    <div>
                                        <p className="font-medium">{participant.name}</p>
                                        <p className="text-sm text-muted-foreground">{participant.email}</p>
                                    </div>
                                </div>
                            ))}
                        </div>
                    </DialogContent>
                </Dialog>
                <Dialog open={showConfirmDialog} onOpenChange={setShowConfirmDialog}>
                    <DialogTrigger asChild>
                        <Button>Register</Button>
                    </DialogTrigger>
                    <DialogContent>
                        <DialogHeader>
                            <DialogTitle>Confirm Registration</DialogTitle>
                            <DialogDescription>
                                Are you sure you want to register for {event.name}?
                            </DialogDescription>
                        </DialogHeader>
                        <DialogFooter>
                            <Button variant="outline" onClick={() => setShowConfirmDialog(false)}>Cancel</Button>
                            <Button onClick={handleRegister}>Confirm</Button>
                        </DialogFooter>
                    </DialogContent>
                </Dialog>
            </CardFooter>
        </Card>
    )
}

